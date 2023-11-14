package br.edu.ifpi.entities;

public class Teacher {
    private String name;
    private String id;
    private String email;
    
    public Teacher(String name, String id, String email){
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
