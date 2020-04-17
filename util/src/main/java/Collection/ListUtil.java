/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-09 9:59
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package Collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ListUtil {
    @Test
    public void  spiltByNum(){
        int step=3;//指定按照每三步进行分组
        List<String> list= Arrays.asList("1","2","3");

        list.stream().spliterator().trySplit();

    }







}
