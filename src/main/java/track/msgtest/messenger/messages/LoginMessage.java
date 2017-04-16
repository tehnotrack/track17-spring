package track.msgtest.messenger.messages;

/**
 *
 */
public class LoginMessage extends Message {
    private String name;
    private String pass;

    public LoginMessage(String name, String pass) {
        type = Type.MSG_LOGIN;
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
