package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Books;

public interface BookRepository extends JpaRepository<Books,Integer>{
	@Query("select s from Books s where s.id=?1 and s.name=?2" )
	public List<Books> getBooksByAuthor(String id,String name);

	//native query
	@Query("select s from Books s where s.name=:name")
	public List<Books> getBookByName(String name);


//DML
@Modifying
@Query("delete from Books s where s.name=?1")
public int deleteBookByName(String name);

@Modifying
@Query("update Books e set e.name=?1 where e.author=?2")
public int UpdateBookByName(String name,String author);

List<Books> findByNameStartingWith(String prefix);
List<Books> findByNameEndingWith(String suffix);
List<Books> findByAuthor(String author);
}
