package utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class StreamUtil {
    /**
     * 1KB
     */
    public static final int BUF_SIZE = 256;

    public static final String ENCODE = "UTF-8";

    /**
     * zip file
     *
     * @param zipfile
     * @param inputFile
     * @throws Exception
     */
    public static void zip(File zipfile, File inputFile)
            throws Exception {
        ZipOutputStream out = null;
        BufferedOutputStream bo = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipfile));
            bo = new BufferedOutputStream(out);
            zip(out, inputFile, inputFile.getName(), bo);
        } catch (Exception e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(bo);
        }
    }

    public static void zip(ZipOutputStream out, File f, String base, BufferedOutputStream bo)
            throws Exception {
        if (f.isDirectory()) {
            List<File> files = Arrays.asList(f.listFiles());
            Collections.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (o1.isDirectory() && o2.isFile()) {
                        return -1;
                    }
                    if (o1.isFile() && o2.isDirectory()) {
                        return 1;
                    }
                    return o1.getName().compareTo(o2.getName());
                }
            });
            for (File subFile : files) {
                String dir = base;
                if (StringUtils.isNotEmpty(dir)) {
                    dir = base + File.separator + subFile.getName();
                } else {
                    dir = subFile.getName();
                }
                zip(out, subFile, dir, bo);
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            BufferedInputStream bi = null;
            try {
                bi = new BufferedInputStream(new FileInputStream(f));
                byte[] b = new byte[BUF_SIZE];
                int length = -1;
                while ((length = bi.read(b)) != -1) {
                    bo.write(b, 0, length);
                }
                bo.flush();
            } catch (Exception e) {
                throw e;
            } finally {
                IOUtils.closeQuietly(bi);
                out.closeEntry();
            }
        }
    }

    /**
     * compress String to bytebuffer as UTF-8
     *
     * @param input
     * @return
     * @throws Exception
     */
    public static ByteBuffer compressString(String input)
            throws Exception {
        ByteArrayOutputStream baos = null;
        GZIPOutputStream os = null;
        ByteBuffer buffer = null;
        try {
            // Compress the UTF-8 encoded String into a byte[]
            baos = new ByteArrayOutputStream();
            os = new GZIPOutputStream(baos);
            byte[] bytes = input.getBytes(ENCODE);
            os.write(bytes);
            os.finish();
            byte[] compressedBytes = baos.toByteArray();
            buffer = ByteBuffer.wrap(compressedBytes);
        } catch (Exception e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(baos);
            IOUtils.closeQuietly(os);
        }
        return buffer;
    }

    /**
     * decode bytebuffer to String with UTF-8
     *
     * @param input
     * @return
     * @throws Exception
     */
    public static String decodeBinary2String(ByteBuffer input)
            throws Exception {
        byte[] bytes = input.array();
        ByteArrayOutputStream baos = null;
        GZIPInputStream is = null;
        try {
            baos = new ByteArrayOutputStream();
            is = new GZIPInputStream(new ByteArrayInputStream(bytes));
            int chunkSize = 1024;
            byte[] buffer = new byte[chunkSize];
            int length = 0;
            while ((length = is.read(buffer, 0, chunkSize)) != -1) {
                baos.write(buffer, 0, length);
            }
            bytes = baos.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(baos);
        }
        return new String(bytes, ENCODE);
    }

    /**
     * combine bytebuffer to string
     *
     * @param list
     * @return
     * @throws Exception
     */
    public static ByteBuffer combineBytebuffers(List<ByteBuffer> list) {
        int length = 0;
        for (ByteBuffer bt : list) {
            length += bt.capacity();
        }
        byte[] bytes = new byte[length];
        int offset = 0;
        for (ByteBuffer bt : list) {
            System.arraycopy(bt.array(), 0, bytes, offset, bt.capacity());
            offset += bt.capacity();
        }
        return ByteBuffer.wrap(bytes);
    }

    /**
     * splite bytebuffer to array by max length
     *
     * @param buffer
     * @param max_length
     * @return
     */
    public static List<ByteBuffer> spliteBytebuffer(ByteBuffer buffer, int max_length) {
        List<ByteBuffer> list = new ArrayList<ByteBuffer>();
        for (int i = 0; i < buffer.capacity(); i += max_length) {
            int length = buffer.capacity() - i >= max_length ? max_length : buffer.capacity() - i;
            byte[] bytes = new byte[length];
            buffer.get(bytes, 0, length);
            ByteBuffer descBuffer = ByteBuffer.wrap(bytes);
            list.add(descBuffer);
        }
        return list;
    }

}
