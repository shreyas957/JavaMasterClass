package com.shreyas.customeannotations;

class CustomClass {
    @MyFiledAnnotation(value = 5)
    private String name;
    private int age;
    private String address;

    public CustomClass(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}

public class MyClass {
    public static void main(String[] args) {
        CustomClass customClass = new CustomClass("Shreyas", 25, "Pune");
        System.out.println(customClass);

        // Using reflection to get the value of the field
        try {
            MyFiledAnnotation annotation = CustomClass.class.getDeclaredField("name").getAnnotation(MyFiledAnnotation.class);
            System.out.println("Value of the field: " + annotation.value());

            //  now let say we want to take value and print the field by value times.
            for (int i = 0; i < annotation.value(); i++) {
                System.out.println(customClass.getName());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
