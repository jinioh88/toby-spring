package user.service;

import user.domain.User;

public class TestUserService extends UserService {
    private String id;

    TestUserService(String id) {
        this.id = id;
    }

    @Override
    protected void upgradedLevel(User user) {
        if(user.getId().equals(this.id)) throw new TestUserServiceException();
        super.upgradedLevel(user);
    }

    static class TestUserServiceException extends RuntimeException {
    }
}
