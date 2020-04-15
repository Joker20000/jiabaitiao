package com.bootdo.Loan.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.Map;


/**
 * @Description: TODO
 * @author HWJ
 * @date 2018年4月25日
 */
public interface AccountDao {
	/**
	 * 保存账号信息
	 *
	 * @param accountNo 	账号
	 * @param eid			企业ID
	 * @return
	 */
	@Insert("INSERT INTO t_act_account(account_no,e_id) VALUES (#{accountNo},#{eid})")
	int insertAccount(@Param("accountNo") String accountNo, @Param("eid") String eid);

	/**
	 * 添加当前可用授信余额
	 *
	 * @param accountNo
	 * @param account
	 * @return
	 */
	@Update("UPDATE t_act_account SET usable_limit = (CAST(usable_limit as DECIMAL(30,2)) + CAST(#{account} as DECIMAL(30,2))) WHERE CAST(credit_limit as DECIMAL(30,2)) >= (CAST(usable_limit as DECIMAL(30,2)) + CAST(#{account} as DECIMAL(30,2))) AND ACCOUNT_NO = #{accountNo}")
	int addUsableLimit(@Param("accountNo") String accountNo, @Param("account") String account);

	/**
	 * 减少当前可用授信余额
	 *
	 * @param accountNo
	 * @param account
	 * @return
	 */
	@Update("UPDATE t_act_account SET usable_limit = (CAST(usable_limit as DECIMAL(30,2)) - CAST(#{account} as DECIMAL(30,2))) WHERE (CAST(usable_limit as DECIMAL(30,2)) - CAST(#{account} as DECIMAL(30,2))) >= 0 AND ACCOUNT_NO = #{accountNo}")
	int subtractUsableLimit(@Param("accountNo") String accountNo, @Param("account") String account);


	/**
	 *授信更新额度及可用额度
	 * @param accountNo
	 * @param account
	 * @return
	 */
	@Update("UPDATE t_act_account SET credit_limit = #{account},usable_limit = #{account} WHERE ACCOUNT_NO = #{accountNo}")
	int updateUsableLimit(@Param("accountNo") String accountNo,@Param("account") String account);


	/**
	 * 查询当前账户额度值
	 *
	 * @param accountNo
	 * @return
	 */
	@Select("SELECT ifnull(credit_limit,0) AS creditLimit,ifnull(usable_limit,0) AS usableLimit FROM t_act_account WHERE account_no = #{accountNo}")
	Map<String,String> queryAccountLimit(@Param("accountNo") String accountNo);

	/**
	 * 添加交易流水记录
	 * @param relevantOrder     关联订单号
	 * @param accountNo          账户编号
	 * @param amount             交易金额
	 * @param addOrSub           变动类型('01'.增加,'02'.减少)
	 * @param busType            业务类型('01'.借款,'02'.还款,'03'.授信)
	 * @param creditLimit        交易后授信额度
	 * @param usableLimit        交易后可用授信余额
	 * @param state               交易状态('0'.失败,'1'.成功)
	 */
	@Insert("INSERT INTO t_act_account_flow(relevant_order,account_no,amount,add_or_sub,bus_type,credit_limit,usable_limit,state) VALUES (#{relevantOrder},#{accountNo},#{amount},#{addOrSub},#{busType},#{creditLimit},#{usableLimit},#{state})")
	void insertFlow(@Param("relevantOrder") String relevantOrder, @Param("accountNo") String accountNo, @Param("amount") String amount, @Param("addOrSub") String addOrSub, @Param("busType") String busType, @Param("creditLimit") String creditLimit, @Param("usableLimit") String usableLimit, @Param("state") String state);

}
