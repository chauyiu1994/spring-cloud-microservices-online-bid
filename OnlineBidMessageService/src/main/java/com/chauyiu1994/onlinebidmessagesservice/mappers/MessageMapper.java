package com.chauyiu1994.onlinebidmessagesservice.mappers;

import com.chauyiu1994.onlinebidmessagesservice.domain.Message;
import com.chauyiu1994.onlinebidmessagesservice.stream.addFriend.AddFriendModel;
import com.chauyiu1994.onlinebidmessagesservice.stream.unFriend.UnFriendModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target = "fromUserId", source = "friendId")
    @Mapping(target = "toUserId", source = "userId")
    @Mapping(target = "messageType", defaultValue = "addFriendRequestAccepted")
    Message addFriendModelToMessage(AddFriendModel model);

    @Mapping(target = "fromUserId", source = "friendId")
    @Mapping(target = "toUserId", source = "userId")
    @Mapping(target = "messageType", defaultValue = "unFriendByUser")
    Message unFriendModelToMessage(UnFriendModel model);
}
