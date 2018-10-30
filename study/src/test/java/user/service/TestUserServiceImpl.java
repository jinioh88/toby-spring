package user.service;

import user.domain.User;

public class TestUserServiceImpl extends UserServiceImpl {
    private String id;

    TestUserServiceImpl(String id) {
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
