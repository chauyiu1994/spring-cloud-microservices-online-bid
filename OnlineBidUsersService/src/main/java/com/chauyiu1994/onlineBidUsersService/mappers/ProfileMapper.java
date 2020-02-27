package com.chauyiu1994.onlineBidUsersService.mappers;

import com.chauyiu1994.onlineBidUsersService.domain.Profile;
import com.chauyiu1994.onlineBidUsersService.models.profile.GeneralProfileRequest;
import com.chauyiu1994.onlineBidUsersService.models.profile.GeneralProfileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    Profile generalProfileRequestToProfile(GeneralProfileRequest generalProfileRequest);

    GeneralProfileResponse profileToGeneralProfileResponse(Profile profile);
}
