package ucll.gip.gip4_2dezit.dto;

public class AccountDTO {
    private int id;
    private String role;
    private String name;
    private String address;
    private String phone;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountDTO(int id, String role, String name, String address, String phone, String email) {
        setId(id);
        setRole(role);
        setName(name);
        setAddress(address);
        setPhone(phone);
        setEmail(email);
    }
}
