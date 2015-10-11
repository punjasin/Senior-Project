package tabs.service.currentUser;

import tabs.entity.CurrentUser;
import tabs.entity.Role;

public class CurrentUserServiceBean implements CurrentUserService{
	
	@Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
		
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }

}
