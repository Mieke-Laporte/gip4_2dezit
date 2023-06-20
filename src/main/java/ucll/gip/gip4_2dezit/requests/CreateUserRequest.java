package ucll.gip.gip4_2dezit.requests;

import ucll.gip.gip4_2dezit.model.Role;
import ucll.gip.gip4_2dezit.model.User;

public class CreateUserRequest {
    private String name;
    private String address;
    private String contactInformation;
    private String rawPassword;

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

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public User toUser(Role role){
        User user = new User();
        user.setName(this.getName());
        user.setAddress(this.getAddress());
        user.setContactInformation(this.getContactInformation());
        user.setRole(role);
        return user;
    }
}
