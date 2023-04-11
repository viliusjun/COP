package com.example.lab1.mybatis.dao;

import com.example.lab1.mybatis.model.Genre;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GENRE
     *
     * @mbg.generated Tue Apr 11 20:50:40 EEST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GENRE
     *
     * @mbg.generated Tue Apr 11 20:50:40 EEST 2023
     */
    int insert(Genre row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GENRE
     *
     * @mbg.generated Tue Apr 11 20:50:40 EEST 2023
     */
    Genre selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GENRE
     *
     * @mbg.generated Tue Apr 11 20:50:40 EEST 2023
     */
    List<Genre> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GENRE
     *
     * @mbg.generated Tue Apr 11 20:50:40 EEST 2023
     */
    int updateByPrimaryKey(Genre row);
}