package and_401.contactsselectionapplication.Models;
import java.io.Serializable;

public class ContactModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String phone;
    private String website;

    public ContactModel(){}

    public ContactModel(String name,String phone,String website){
        this.name = name;
        this.phone = phone;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
