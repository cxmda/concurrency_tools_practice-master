package immutable;

/**
 * @author chen
 * @create 2020-07-12 14:59
 */
public class FinalMethodDemo {

    public void drink() {

    }

    public final void eat() {

    }

    public static void sleep() {

    }
}

class SubClass extends FinalMethodDemo {

    @Override
    public void drink() {
        super.drink();
    }

//    public final void eat(){
//
//    }

    public static void sleep(){

    }
}
