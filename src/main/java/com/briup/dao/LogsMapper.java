package com.briup.dao;

import com.briup.bean.Logs;
import com.briup.bean.LogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    long countByExample(LogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    int deleteByExample(LogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    int insert(Logs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    int insertSelective(Logs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    List<Logs> selectByExample(LogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    Logs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    int updateByExampleSelective(@Param("record") Logs record, @Param("example") LogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    int updateByExample(@Param("record") Logs record, @Param("example") LogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    int updateByPrimaryKeySelective(Logs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_logs
     *
     * @mbg.generated Mon Nov 11 20:23:50 CST 2019
     */
    int updateByPrimaryKey(Logs record);
}