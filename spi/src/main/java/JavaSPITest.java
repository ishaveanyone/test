import com.dist.Bumblebee;
import com.dist.OptimusPrime;
import com.dist.Robot;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by Administrator on 2019/8/7.
 */
public class JavaSPITest {

    public static void main(String[] args) throws Exception{
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(robot -> {
            if(robot instanceof OptimusPrime){
                robot.sayHello();
            }
        });

    }
}
