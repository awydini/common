package net.aydini.common;

import net.aydini.common.mapper.EntityMapper;
import net.aydini.common.mapper.Mapper;
import net.aydini.common.model.annotation.MappedField;
import net.aydini.common.spring.BeanUtil;
import net.aydini.common.spring.SpringAwareEntityMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan
public class Application {


    public static void main(String ... args )
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);


        SpringAwareEntityMapper springAwareEntityMapper = applicationContext.getBean("springAwareEntityMapper", SpringAwareEntityMapper.class);

        EntityMapper entityMapper = EntityMapper.getInstance();


        A a = new A();
        a.setFamily("nasr");
        a.setName("aydin");

//        B b = springAwareEntityMapper.map(a, B.class);

        B b = entityMapper.map(a,B.class);
        System.out.println(b.getFamily());
        System.out.println(b.getName());

    }




    public static class A
    {
        private String name;
        private String family;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }
    }



    public static class B
    {
        @MappedField(fieldName = "name")
        private String name;

        @MappedField(fieldName = "family", isCustom = true,mapperClass = BMapper.class)
        private String family;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }
    }

//    @Component
    public static class BMapper implements Mapper<A,String>
    {


        @Override
        public String map(A input) {
            return input.getName() + " mapper";
        }
    }
}
