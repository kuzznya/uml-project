package com.github.kuzznya.bs.userservice.messaging;

import com.github.kuzznya.bs.userservice.model.UserInfoResult;
import com.github.kuzznya.bs.userservice.model.UserRequest;
import com.github.kuzznya.bs.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoListener {

    private final UserService userService;

    @RabbitListener(queuesToDeclare = @Queue(name = "users.requests", durable = "true", exclusive = "false"))
    public UserInfoResult listen(UserRequest request) {
        return userService.findById(request.getId())
                .map(user -> new UserInfoResult(true, user))
                .orElse(new UserInfoResult(false, null));
    }
}
