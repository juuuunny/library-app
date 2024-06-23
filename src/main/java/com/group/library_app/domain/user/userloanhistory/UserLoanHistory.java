package com.group.library_app.domain.user.userloanhistory;

import com.group.library_app.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class UserLoanHistory {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn
    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn;

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void returnBook() {
        this.isReturn = true;
    }
}
