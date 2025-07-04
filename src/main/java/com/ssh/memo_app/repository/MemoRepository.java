package com.ssh.memo_app.repository;


import com.ssh.memo_app.model.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoRepository {
    private final JdbcTemplate jdbcTemplate; //JDBC를 사용하기 위한 만들어져있는 템플릿

    @Autowired
    private Environment env; //환경 변수 확인할수 있는 객체

    public MemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Memo> memoRowMapper = (resultSet, roNum) ->
            new Memo(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content")
            ); //RowMapper<Memo>  ResultSet의 각 행을 Memo 객체로 변환 resultset : 행의 데이터 / rowNum : 행의 번호

    //resultSet.getInt("id")	DB 컬럼 "id" 값을 정수로 가져옴

    public List<Memo> findAll() {
        System.out.println("APP_NAME:" + env.getProperty("APP_NAME"));//전체 조회
        return jdbcTemplate.query(
                "Select * FROM memo ORDER BY id DESC",
                memoRowMapper
        );
    }

    public Memo findById(int id) {  //queryForObject 단일 조회
        return jdbcTemplate.queryForObject(
                "SELECT * FROM memo WHERE id = ?",
                memoRowMapper,
                id
        );
    }

    public void save(String title, String content) {
        jdbcTemplate.update(
                "INSERT INTO memo (title, content) VALUES (?, ?)",
                title, content); //()안에 내용은 query문 - db문구
        // update 는 db정보를 바꾸겠다는 의미  //query 가 조회하는것

    }// 메모를 하나 생성하는 기능 , id값은 자동으로 채워지고 ,제목이랑 내용만 적어주면됨

    public void update(int id, String title, String content) {
        jdbcTemplate.update(
                "UPDATE memo SET title = ?, content = ? WHERE id =?",
                title, content, id
        );
    }


    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM memo WHERE id = ?",
                id
        );
    }

}
