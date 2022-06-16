//package models;
//
//import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
//import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
//
////сюда добавить разные поля ответа, геттеры и сеттеры к полям
//public class UserData {
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    private Long id;
//    private String email;
//    @JsonProperty("first_name")
//    private String firstName;
//    @JsonProperty("last_name")
//    private String lastName;
//
//    private String name;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//}
