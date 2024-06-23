package com.group.library_app.service.user;

import com.group.library_app.domain.user.User;
import com.group.library_app.domain.user.UserRepository;
import com.group.library_app.dto.user.request.UserCreateRequest;
import com.group.library_app.dto.user.request.UserUpdateRequest;
import com.group.library_app.dto.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    @Transactional
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());

    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());

    }

    @Transactional
    public void deleteUser(String name) {
        User user=userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }
}
