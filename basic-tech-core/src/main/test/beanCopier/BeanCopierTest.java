package beanCopier;

import cglib.beanCopier.LackOfSetter;
import cglib.beanCopier.OrderDto;
import cglib.beanCopier.OrderEntity;
import cglib.beanCopier.PropWithDiffType;
import net.sf.cglib.beans.BeanCopier;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author:Cooper
 * @Date:2019/1/26 13:03
 */
public class BeanCopierTest {


    /**
     * 属性名称、类型都相同:结果OK
     */
    @Test
    public void normalCopyTest() {
        OrderEntity entity = new OrderEntity();
        entity.setId(1);
        entity.setName("orderName");
        BeanCopier beanCopier = BeanCopier.create(OrderEntity.class, OrderDto.class, false);
        OrderDto dto = new OrderDto();
        beanCopier.copy(entity, dto, null);
        Assert.assertEquals(1, dto.getId());
        Assert.assertEquals("orderName", dto.getName());
    }


    /**
     * 属性名称相同、类型不同：名称相同而类型不同的属性不会被拷贝。
     */
    @Test
    public void sameNameDifferentTypeCopyTest() {
        OrderEntity entity = new OrderEntity();
        entity.setId(1);
        entity.setName("orderName");
        final BeanCopier copier = BeanCopier.create(OrderEntity.class, PropWithDiffType.class, false);
        PropWithDiffType dto = new PropWithDiffType();
        copier.copy(entity, dto, null);
        Assert.assertEquals(null, dto.getId()); // OrderEntity的id为int类型，而PropWithDiffType的id为Integer类型，不拷贝
        Assert.assertEquals("orderName", dto.getName());
    }


    /**
     * 源类和目标类有相同的属性(两者的getter都存在),但目标类的setter不存在
     * 创建BeanCopier的时候抛异常。
     */
    @Test
    public void targetLackOfSetterCopyTest() {
        OrderEntity entity = new OrderEntity();
        entity.setId(1);
        entity.setName("orderName");
        final BeanCopier copier = BeanCopier.create(OrderEntity.class, LackOfSetter.class, false);  // 抛NullPointerException
        LackOfSetter dto = new LackOfSetter();
        copier.copy(entity, dto, null);
    }


    /**
     *  源类或目标类的setter比getter少:拷贝OK。此时的setter多余，但不会报错。
     */
    @Test
    public void sourceLackOfSetterCopyTest() {
        LackOfSetter source = new LackOfSetter(1, "throne");
        final BeanCopier copier = BeanCopier.create(LackOfSetter.class, OrderDto.class, false);
        OrderDto dto = new OrderDto();
        copier.copy(source, dto, null);
        Assert.assertEquals(1, dto.getId());
        Assert.assertEquals("throne", dto.getName());
    }


}
