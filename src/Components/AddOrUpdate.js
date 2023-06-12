import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import BookService from '../Service/BookService';

const AddOrUpdate=()=> {
  const nav=useNavigate();
  const[name,setname]=useState('');
  const[sold,setsold]=useState('');
  const[author,setauthor]=useState('');
  const[price,setprice]=useState('');
  const[available,setavailable]=useState('');
  const {id} = useParams();
    const saveOrUpdateBook = (e) => {
      e.preventDefault();
      if(sold.length==0||available.length==0||price.length==0||name.length==0||author.length==0){
        alert("Enter All fields")
      }
      else{
      if (window.confirm("Confirm Details!") == true) {
        e.preventDefault();
          const book = {id, name, sold,author,available,price}
          if(id){
              BookService.updateBook(id, book).then((response) => {
                  nav('/home')
              }).catch(error => {
                  console.log(error)
              })
  
          }else{
              BookService.createBook(book).then((response) =>{
                  console.log(response.data)
                  nav('/home');
              }).catch(error => {
                  console.log(error)
              })
          }
        }
      }
    } 
  
      useEffect(() => {
          BookService.getBookById(id).then((response) =>{
              setname(response.data.name)
              setsold(response.data.sold)
              setauthor(response.data.author)
              setavailable(response.data.available)
              setprice(response.data.price)
          }).catch(error => {
              console.log(error)
          })
      }, [])
      const title = () => {

        if(id){
            return <h1>Update Book</h1>
        }else{
            return <h1>Add Book</h1>
        }
    }
  return (
    <div id="body">
    <div className="signup-form">
    <div className="container">
      <div className="header">
        {title()}
        <p>Enter Book Details</p>
      </div>
      <form>
        <div className="input">
          <input type="text" placeholder="name" value={name} onChange={(e)=>setname(e.target.value)}  />
        </div>
        <div className="input">
          <input type="text" placeholder="Author" value={author}  pattern="[a-z]+"
                   onChange={(e)=>setauthor(e.target.value)} />
        </div>
        <div className="input">
          <input type="number" placeholder="Sold Out" value={sold}  onChange={(e)=>setsold(e.target.value)}/>
        </div>
        <div className="input">
          <input type="text" placeholder=" Price" value={price} pattern="[0-9]+"  onChange={(e)=>setprice(e.target.value)}/>
        </div>
        <div className="input">
          <input type="text" placeholder="Stock" value={available} pattern="[0-9]+"   onChange={(e)=>setavailable(e.target.value)} />
        </div>
        
        <input onClick={saveOrUpdateBook} className="e-signup-btn" type="submit" value="Submit" />
      <Link to="/home">  <button className="e-cancel-btn" >Cancel </button></Link>
        </form>
    </div>
  </div>
    </div>
  )
}

export default AddOrUpdate