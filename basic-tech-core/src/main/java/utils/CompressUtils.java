package utils;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompressUtils {
    
    public static File zipAndRemoveFiles(List<File> files, String zipName) {
        
        ZipArchiveOutputStream zaos = null;
        File zipFile = new File(zipName);
        try {
            zipFile = new File(zipName);
            zaos = new ZipArchiveOutputStream(zipFile);
            for (File file : files) {
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
                //zipArchiveEntry.setCreationTime(FileTime.fromMillis(0));
                //zipArchiveEntry.setLastModifiedTime(FileTime.fromMillis(0));
                //zipArchiveEntry.setLastAccessTime(FileTime.fromMillis(0));
                zaos.putArchiveEntry(zipArchiveEntry);
                InputStream ins = null;
                try {
                    ins = new FileInputStream(file);
                    byte[] buffer = new byte[1024 * 5];
                    int len = -1;
                    while ((len = ins.read(buffer)) != -1) {
                        zaos.write(buffer, 0, len);
                    }
                    zaos.closeArchiveEntry();
                }
                finally {
                    IOUtils.closeQuietly(ins);
                }
            }
            
            zaos.finish();
            
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            IOUtils.closeQuietly(zaos);
        }
        
        for (File file : files) {
            FileUtils.deleteQuietly(file);
        }
        
        return zipFile;
    }
    
    public static File zipFiles(List<File> files, String zipName, boolean remove) {
        File zipFile = new File(zipName);
        if (files != null) {
            Map<String, File> map = new HashMap<String, File>();
            for (File f : files) {
                list(f, null, map);
            }
            if (!map.isEmpty()) {
                try {
                    ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(zipFile);
                    for (Map.Entry<String, File> entry : map.entrySet()) {
                        File file = entry.getValue();
                        ZipArchiveEntry zae = new ZipArchiveEntry(file, entry.getKey());
                        //zae.setCreationTime(FileTime.fromMillis(0));
                        //zae.setLastModifiedTime(FileTime.fromMillis(0));
                        //zae.setLastAccessTime(FileTime.fromMillis(0));
                        zaos.putArchiveEntry(zae);
                        InputStream is = new FileInputStream(file);
                        byte[] b = new byte[1024 * 5];
                        int i = -1;
                        while ((i = is.read(b)) != -1) {
                            zaos.write(b, 0, i);
                        }
                        zaos.closeArchiveEntry();
                        IOUtils.closeQuietly(is);
                    }
                    zaos.finish();
                    IOUtils.closeQuietly(zaos);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (remove) {
            for (File file : files) {
                FileUtils.deleteQuietly(file);
            }
        }
        return zipFile;
    }
    
    private static void list(File f, String parent, Map<String, File> map) {
        String name = f.getName();
        if (parent != null) {
            name = parent + "/" + name;// 设置在zip包里的相对路径
        }
        if (f.isFile()) {
            map.put(name, f);
        }
        else if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                list(file, name, map);
            }
        }
    }
    
}
