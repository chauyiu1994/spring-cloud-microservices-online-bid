package com.chauyiu1994.onlineBidUsersService.stream;

import com.chauyiu1994.onlineBidUsersService.stream.addFriend.AddFriendModel;
import com.chauyiu1994.onlineBidUsersService.stream.addFriend.AddFriendSource;
import com.chauyiu1994.onlineBidUsersService.stream.unFriend.UnFriendModel;
import com.chauyiu1994.onlineBidUsersService.stream.unFriend.UnFriendSource;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
@AllArgsConstructor
@EnableBinding({AddFriendSource.class, UnFriendSource.class})
public class SendMessageUtil {

    private ExecutorService messagePool;
    private ApplicationContext ctx;

    // inform friend that the user has accepted to add friend request
    public void sendAddedFriendMessage(AddFriendModel model) {

        messagePool.execute(() -> {
            System.out.println("payload is " + model);
            MessageChannel channel = ctx.getBean(AddFriendSource.ADD_FRIEND, MessageChannel.class);
            boolean send = channel.send(MessageBuilder.withPayload(model).build());
            System.out.println("send data " + send);
        });
    }

    // inform friend that the user has un-friended him
    public void sendUnFriendMessage(UnFriendModel model) {

        messagePool.execute(() -> {
            System.out.println("payload is " + model);
            MessageChannel channel = ctx.getBean(UnFriendSource.UN_FRIEND, MessageChannel.class);
            boolean send = channel.send(MessageBuilder.withPayload(model).build());
            System.out.println("send data " + send);
        });
    }

}
