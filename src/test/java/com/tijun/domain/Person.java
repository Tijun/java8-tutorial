package com.tijun.domain;

public class Person {

    private String name;

    private int age;

    private String email;

    private float salary;

    public String getName() {
        return name;
    }
    public Person(){}

    public Person(String name, int age, String email, float salary) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (Float.compare(person.salary, salary) != 0) return false;
        if (!name.equals(person.name)) return false;
        return email.equals(person.email);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + email.hashCode();
        result = 31 * result + (salary != +0.0f ? Float.floatToIntBits(salary) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }
}
