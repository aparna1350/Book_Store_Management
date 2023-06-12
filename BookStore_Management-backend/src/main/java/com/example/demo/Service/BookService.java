package com.example.demo.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Books;
import com.example.demo.Repository.BookRepository;

import jakarta.transaction.Transactional;



@Service
public class BookService {
	@Autowired
	BookRepository employeeRepository;
	/*public  List <Books> sortinform(String field){
		return employeeRepository.findAll(Sort.by(field));
		}
		public Page<Books> paging (int page,int size) {
			PageRequest paging=PageRequest.of(page,size);
			return employeeRepository.findAll(paging);
		}
		public Page<Books> SortingAndPaging(int page,int size,String field){
			Pageable paging=PageRequest.of(page, size).withSort(Sort.by(field));
			return employeeRepository.findAll(paging);
		}
		*/
	 public List<Books> getAllBooks(){
	        return employeeRepository.findAll();
	    }

	    public Books createBook(Books book) {
	        return employeeRepository.save(book);
	    }

	    public Books getBookById( int id){
	        Books employee = employeeRepository.findById(id).get();
	        return employee;
	    }

		public Books updateBookDetails( Books b)
		{
			System.out.println("Changes Made Have Been Successfully Updated");
			return employeeRepository.save(b);		
		}
		public String deleteDetails(int id)
		{
			employeeRepository.deleteById(id);
			return "Id : "+id+" is deleted";
		}
	    public String deleteAllDetails()
	    {
	    	employeeRepository.deleteAll();
	    	return "All employees deleted";
	    }
	    
	    
	    //sorting
	    
	    public List<Books> sortBooks(String field)
	    {
	   	//ascending findAll(sort sort) and no change in controller method for both//
	   	 //return studRepository.findAll(Sort.by(field));
	   	 
	   	 //descending findAll(Sort.by(Direction,field))
	   	 return employeeRepository.findAll(Sort.by(Direction.DESC, field));
	    }
	    
	    
	    //pagination
	    
	    public List<Books> pagingBooks(int offset,int pageSize)
	    {
	   	 Pageable paging=PageRequest.of(offset,pageSize);
	   	 Page<Books> studData=employeeRepository.findAll(paging);
	   	 List<Books>studList=studData.getContent();
	   	 return studList;
	    }
	    
	    
	    //pagination and sorting
	    
	    public List<Books> pagingAndSortingBooks(int offset,int pageSize,String field) 
	    {
	   	Pageable paging = PageRequest.of(offset, pageSize).withSort(Sort.by(field));
	   	Page<Books> stud=employeeRepository.findAll(paging);
	   	return stud.getContent();
	    }
	    
	    //JPA QUERY
	    
	    public List<Books> fetchBooksByNamePrefix(String prefix)
	    {
	   	  return employeeRepository.findByNameStartingWith(prefix);
	    }
	    
	    public List<Books> fetchBooksByNameSuffix(String suffix)
	    {
	   	 return employeeRepository.findByNameEndingWith(suffix);
	    }
	    
	    public List<Books> getBooksByAuthor(String id,String name)
	    {
	   	  return employeeRepository.getBooksByAuthor(id, name);
	    }
	    @Transactional
	    public int deleteStudentByName(String name)
	    {
	    	return employeeRepository.deleteBookByName(name);
	    }
	    @Transactional
	    public int update(String name,String author)
	    {
	   	 return employeeRepository.UpdateBookByName(name,author);
	    }
	   //native
	   public List<Books> getBookByName(String name)
	   {
	   	return employeeRepository.getBookByName(name);
	   }

}
