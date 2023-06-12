import axios from 'axios'

const URL = 'http://localhost:8080/books';

class BookService{

    getAllBooks(){
        return axios.get(URL)
    }

    createBook(book){
        return axios.post(URL,book)
    }

    getBookById(bookId){
        return axios.get(URL + '/' + bookId);
    }

    updateBook(bookId, book){
        return axios.put(URL + '/' +bookId, book);
    }

    deleteBook(bookId){
        return axios.delete(URL + '/' + bookId);
    }
    deleteAllBook(bookId){
        return axios.delete(URL);
    }
}

export default new BookService();