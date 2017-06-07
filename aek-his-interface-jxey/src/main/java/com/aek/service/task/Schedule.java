package com.aek.service.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aek.persistence.oracle.beans.BdHplGoodsSpecs;
import com.aek.persistence.oracle.beans.BdHplGoodses;
import com.aek.persistence.oracle.beans.DataErrorInfo;
import com.aek.persistence.oracle.beans.HisDeptInfo;
import com.aek.persistence.oracle.beans.HisFeeInfo;
import com.aek.persistence.oracle.beans.HisInpatientInfo;
import com.aek.persistence.oracle.beans.HisUserInfo;
import com.aek.persistence.oracle.beans.SdHplStockOut;
import com.aek.persistence.oracle.beans.SdHplStockOutItems;
import com.aek.persistence.oracle.beans.ZyChargeInterface;
import com.aek.persistence.oracle.beans.ZyDetailCharge;
import com.aek.service.transfer.ErrorInfoService;
import com.aek.service.transfer.HisDeptInfoService;
import com.aek.service.transfer.HisFeeInfoService;
import com.aek.service.transfer.HisInpatientInfoService;
import com.aek.service.transfer.HisUerInfoService;
import com.aek.service.transfer.HplGoodsSpecsService;
import com.aek.service.transfer.HplGoodsesService;
import com.aek.service.transfer.SdHplStockOutItemsService;
import com.aek.service.transfer.SdHplStockOutService;
import com.aek.service.transfer.ZyChargeInfoService;
import com.aek.service.transfer.ZyDetailChargeService;
import com.mb.common.util.DateUtils;
import com.mb.common.util.ObjectUtils;

public class Schedule {

    @Autowired
    private HisDeptInfoService hisDeptInfoService;


    private static Logger log = Logger.getLogger(Schedule.class);

    /**
     * 服务启动时执行
     * 
     * @Description
     * @author sunhanbin
     * @date 2015-4-18下午09:01:27
     */

    public void runBySeverStart() {
        log.info("--------------Start：runByServerStart()---------------");
        int size = 500;

        log.info("--------------End  ：runByServerStart()---------------");
    }

    /**
     * 每小时执行一次
     * 
     * @Description
     * @author sunhanbin
     * @date 2015-4-18下午09:01:38
     */
    public void runByHour() {
        log.info("--------------Start：runByHour()-----------------");
        log.info("--------------End  ：runByHour()-----------------");
    }

    /**
     * 每十分钟执行一次
     * 
     * @Description
     * @author sunhanbin
     * @date 2015-4-18下午09:04:11
     */

    public void runByTenMinute() {
        log.info("--------------Start：runByTwoMinute()---------------");
        // 连接到oracle的数据源信息
        int size = 500;

        copyDept(size);


        log.info("--------------End  ：runByTwoMinute()---------------");
    }


    private void copyDept(int size) {
        try {
            int i = 1;
            while (i != 0) {
                Integer begin = (i - 1) * size;
                Integer end = i * size + 1;
                List<HisDeptInfo> deptOracleList = null;
                deptOracleList = this.hisDeptInfoService.getOracleList(begin, end);
                if (ObjectUtils.isNotEmpty(deptOracleList)) {
                    List<HisDeptInfo> deptMysqlList = this.hisDeptInfoService.getMysqlList(deptOracleList);
                    this.hisDeptInfoService.copyDeptInfo(deptOracleList, deptMysqlList);
                    i++;
                } else {
                    i = 0;
                }
            }
        } catch (Exception e) {
            log.error("copy DeptInfo had occured a problem : ", e);
        }
    }

}
