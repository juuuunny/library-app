package com.group.library_app.domain.user;

import com.group.library_app.domain.user.userloanhistory.UserLoanHistory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length=25)
    private String name;

    private Integer age;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories=new ArrayList<>();

    public User(String name, Integer age) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
        this.age = age;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName) {
        userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = userLoanHistories.stream().filter(history -> history.getBookName().equals(bookName)).findFirst().orElseThrow(IllegalArgumentException::new);
        targetHistory.returnBook();
    }
}
