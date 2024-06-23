package com.group.library_app.service.book;

import com.group.library_app.domain.book.Book;
import com.group.library_app.domain.book.BookRepository;
import com.group.library_app.domain.user.User;
import com.group.library_app.domain.user.UserRepository;
import com.group.library_app.domain.user.userloanhistory.UserLoanHistoryRepository;
import com.group.library_app.dto.book.request.BookCreateRequest;
import com.group.library_app.dto.book.request.BookLoanRequest;
import com.group.library_app.dto.book.request.BookReturnRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        Book book = bookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);

        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(), false)){
            throw new IllegalArgumentException("이미 대출중인 도서입니다.");
        }

        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);

        user.loanBook(book.getName());
    }

    @Transactional
    public void returnBook(BookReturnRequest request){

        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        user.returnBook(request.getBookName());

    }

}
