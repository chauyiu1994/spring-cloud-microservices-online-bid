package com.chauyiu1994.onlineBidUsersService.services;

import com.chauyiu1994.onlineBidUsersService.domain.Profile;
import com.chauyiu1994.onlineBidUsersService.repositories.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Override
    public Mono<Profile> findByUserId(String userId) {

        return profileRepository.findByUserId(userId);
    }

    @Override
    public Mono<Profile> findById(String id) {

        return profileRepository.findById(id);
    }

    // find whether the two id is valid and add to each other
    @Override
    @Transactional
    public Mono<Profile> addFriend(String id, String friendId) {

        return profileRepository.findByUserIdAndFriendNotAddedYet(id, friendId)
                .flatMap(mainProfile -> {
                    System.out.println(mainProfile);
                    return profileRepository.findByUserId(friendId);
                })
                .flatMap(fdProfile -> profileRepository.addFriend(id, friendId));
    }

    @Override
    public Mono<Profile> removeFriend(String id, String friendId) {

        return profileRepository.findByUserIdAndFriendContains(id, friendId)
                .flatMap(mainProfile -> {
                    System.out.println(mainProfile);
                    return profileRepository.findByUserId(friendId);
                })
                .flatMap(fdProfile -> profileRepository.removeFriend(id, friendId));
    }

    @Override
    public Mono<Profile> createProfile(Profile profile) {

        profile.setLastModifiedTime(LocalDateTime.now());
        return profileRepository.save(profile);
    }

    @Override
    public Mono<Profile> updateProfile(Profile profile) {

        profile.setLastModifiedTime(LocalDateTime.now());
        return profileRepository.save(profile);
    }

    @Override
    public Mono<Void> deleteProfile(String id) {

        return profileRepository.deleteById(id);
    }
}
