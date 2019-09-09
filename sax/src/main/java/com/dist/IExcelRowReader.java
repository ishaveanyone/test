package com.dist;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-05 16:23
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
import java.util.List;
public interface IExcelRowReader {
    /**
     * 业务逻辑实现方法
     *
     * @param sheetIndex
     * @param curRow
     * @param rowlist
     */
    void getRows(int sheetIndex, int curRow, List<String> rowlist);
}