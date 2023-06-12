package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Books;
import com.example.demo.Service.BookService;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
     BookService bookService;
    @Tag(name="Get",description="get data")
    @GetMapping
    public List<Books> getAllBooks(){
    	 List<Books> studList=bookService.getAllBooks();
    	 return studList;
    }

    @PostMapping
    public Books createBook(@RequestBody Books book) {
        return bookService.createBook(book);
    }

    @GetMapping("{id}")
    public Books getBookById(@PathVariable int id)
    {
    	return bookService.getBookById(id);
    }

    @PutMapping("{id}")
	public Books updateBookDetails(@RequestBody Books b)
	{
		return bookService.updateBookDetails(b);		
	}
    @DeleteMapping("{id}")
	public String deleteDetails(@PathVariable int id)
	{
		return bookService.deleteDetails(id);
	}
    @DeleteMapping
    public String deleteAllDetails()
    {
    	return bookService.deleteAllDetails();
    }
 // sorting//

@GetMapping("/sortStudents/{field}")
public List<Books>sortBooks(@PathVariable String field)
{
	return bookService.sortBooks(field); 
}

///pagination


@GetMapping("/pagingStudents/{offset}/{pageSize}")
public List<Books> pagingBooks(@PathVariable int offset,@PathVariable int pageSize)
{
	return bookService.pagingBooks(offset,pageSize);
}


//pagination and sorting

@GetMapping("/pagingAndSortingStudents/{offset}/{pageSize}/{field}")
public List<Books> pagingAndSortingEmployees(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field)
{
	return bookService.pagingAndSortingBooks(offset,pageSize,field);
}

//JPA Query
//http://localhost:8080/fetchStudentsByNamePrefix?prefix=a
@GetMapping("/fetchBooksByNamePrefix")
public List<Books> fetchBooksByNamePrefix(@RequestParam String prefix)
{
	  return bookService.fetchBooksByNamePrefix(prefix);
}

//http://localhost:8080/fetchStudentsByNameSuffix?suffix=n

@GetMapping("/fetchBooksByNameSuffix")
public List<Books> fetchBooksByNameSuffix(@RequestParam String suffix)
{
	return bookService.fetchBooksByNameSuffix(suffix);
}

//http://localhost:8080/fetchStudentsByDepartment/ECE/Minu
@GetMapping("/fetchBooksById/{id}/{name}")
public List<Books> fetchBooksByAuthor(@PathVariable 
		String id,@PathVariable String name)
{
	return bookService.getBooksByAuthor(id, name);
}
@DeleteMapping("/deleteBookByName/{name}")
public String deleteBookByName(@PathVariable String name)
{
int result=bookService.deleteStudentByName(name);
if(result >0)
	return "Student record deleted";
else
	return "Problem occured while deleting";
}
@PutMapping("/update/{name}/{author}")

public String update(@PathVariable String name,@PathVariable String author)

{

	int result=bookService.update(name,author);

	if(result>0)

		return "Updated Successfully";

	else

		return "Problem occur while updating";

}
//NATIVE QUERY
@GetMapping("/getByName/{name}")
public List<Books> getBookByName(@PathVariable String name)
{
return bookService.getBookByName(name);
}

}