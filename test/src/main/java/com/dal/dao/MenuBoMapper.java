package com.dal.dao;

import com.dal.pojo.MenuBo;
import com.dal.pojo.MenuBoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuBoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    int countByExample(MenuBoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    int deleteByExample(MenuBoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String funcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    int insert(MenuBo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    int insertSelective(MenuBo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    List<MenuBo> selectByExample(MenuBoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    MenuBo selectByPrimaryKey(String funcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MenuBo record, @Param("example") MenuBoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MenuBo record, @Param("example") MenuBoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MenuBo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_SYS_FUNCTION
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MenuBo record);
}