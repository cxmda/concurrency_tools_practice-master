package threadlocal;

/**
 * 演示ThreadLocal的用法2：避免传递参数的麻烦
 * @author chen
 * @create 2020-06-15 22:45
 */
public class ThreadLocalNormalUsage06 {

    public static void main(String[] args) {
        new Service1().process("李华");
    }
}

class Service1{

    void process(String username){
        User user = new User(username);
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2{

    void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("service2拿到的user对象：" + user);
        new Service3().process();
    }
}

class Service3{

    void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("service3拿到的user对象：" + user);
    }
}

class UserContextHolder{

    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User{

    private String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
