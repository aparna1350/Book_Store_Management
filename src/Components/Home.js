import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import BookService from '../Service/BookService';
import "./Home.css"

const Home=()=> {
  const nav=useNavigate();
  function myFunction() {
    var x = document.getElementById("table");
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
  }
  const [books, setBooks] = useState([])
  useEffect(() => {
      getAllBooks();
  }, [])

  const getAllBooks = () => {
      BookService.getAllBooks().then((response) => {
          setBooks(response.data)
          console.log(response.data);
      }).catch(error =>{
          console.log(error);
      })
  }

  const deleteBook = (bookId) => {
    if(window.confirm("Sure to Delete?")){
     BookService.deleteBook(bookId).then((response) =>{
      getAllBooks();

     }).catch(error =>{
         console.log(error);
     })
    }
  }
  const deleteAllBook = () => {
    if(window.confirm("Sure to Delete All Books?")){
     BookService.deleteAllBook().then((response) =>{
      getAllBooks();

     }).catch(error =>{
         console.log(error);
     })
    }
  }
  const LogoutHandler=()=>{
    if(window.confirm("Sure to Logout?")){
      nav("/")
    }
  }
  return (
    <div id="container">
            <h1>COZY FLIP</h1>
    <Link to="/add"><button id="addbtn">Add Books</button></Link>
    <button id="viewbtn" onClick={deleteAllBook} >Delete All Books</button>
  
    <table id="table">
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Book_Name</th>
                  <th>Author_Name</th>
                  <th>Sold</th>
                  <th>Price</th>
                  <th>Available</th>
                  <th>Edit</th>
                </tr>
              </thead>
              <tbody>
              {
                books.map(
                    book => (
      <tr>
             <th> {book.id}</th>
             <th> {book.name}</th>
             <th> {book.author}</th>
             <th> {book.sold}</th>
             <th> {book.price}</th>
             <th> {book.available}</th>
             <th><Link  to={`/update/${book.id}`}><button id="actions">Update</button></Link> 
             <button id="actions"  onClick = {() => deleteBook(book.id)}
            > Delete!</button></th>
            
      </tr>
    ))}
              </tbody>
            </table>
    <button id="logout" onClick={LogoutHandler}>Logout</button>
        
    </div>
  )
}

export default Home