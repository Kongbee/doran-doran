package com.purple.hello.service.impl;

import com.purple.hello.dao.RoomDAO;
import com.purple.hello.dao.UserRoomDAO;
import com.purple.hello.dto.in.*;
import com.purple.hello.dto.out.CreateRoomOutDTO;
import com.purple.hello.dto.out.CreateUserRoomJoinOutDTO;
import com.purple.hello.entity.UserRoom;
import com.purple.hello.enu.BoolAlarm;
import com.purple.hello.enu.UserRoomRole;
import com.purple.hello.service.UserRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Service
public class UserRoomServiceImpl implements UserRoomService {
    @Autowired
    private final UserRoomDAO userRoomDAO;
    @Autowired
    private final RoomDAO roomDAO;
    public UserRoomServiceImpl(UserRoomDAO userRoomDAO, RoomDAO roomDAO){
        this.userRoomDAO = userRoomDAO;
        this.roomDAO = roomDAO;
    }
    @Override
    public CreateRoomOutDTO createUserRoom(CreateUserRoomInDTO createUserRoomInDTO, long roomId) {
        return this.userRoomDAO.createUserRoom(createUserRoomInDTO, roomId);
    }

    @Override
    @Transactional
    public CreateUserRoomJoinOutDTO createUserRoomJoin(CreateUserRoomJoinInDTO createUserRoomJoinInDTO) {
        boolean isAlreadyUser = checkUserExist(createUserRoomJoinInDTO.getUserId(), createUserRoomJoinInDTO.getRoomId());
        if(isAlreadyUser){
            return this.userRoomDAO.updateUserRoomRejoin(createUserRoomJoinInDTO);
        }else{
            return this.userRoomDAO.createUserRoomJoin(createUserRoomJoinInDTO);
        }
    }

    @Override
    @Transactional
    public boolean updateRoomName(UpdateRoomNameInDTO updateRoomNameInDTO) throws Exception{
        return userRoomDAO.updateRoomName(updateRoomNameInDTO);
    }

    @Override
    @Transactional
    public boolean updateUserName(UpdateUserNameInDTO updateUserNameInDTO) {
        return userRoomDAO.updateUserName(updateUserNameInDTO);
    }

    @Override
    @Transactional
    public boolean updateMoveAlarm(UpdateMoveAlarmInDTO updateMoveAlarmInDTO) {
        return userRoomDAO.updateMoveAlarm(updateMoveAlarmInDTO);
    }

    @Override
    @Transactional
    public boolean updateSafeAlarm(UpdateSafeAlarmInDTO updateSafeAlarmInDTO) {
        return userRoomDAO.updateSafeAlarm(updateSafeAlarmInDTO);
    }

    @Override
    @Transactional
    public boolean deleteUserRoom(DeleteUserRoomInDTO deleteUserRoomInDTO) {
        final int USER_ROOM_LIMIT = 1;
        UserRoom userRoom = userRoomDAO.readUserRoomByUserRoomId(deleteUserRoomInDTO.getUserRoomId());
        List<UserRoom> userRooms = userRoomDAO.readUserRoomsByRoomIdWithoutUserRoomIdUsingLimit(userRoom.getRoom().getRoomId(), userRoom.getUserRoomId(), USER_ROOM_LIMIT);
        if(userRooms.size() > 0) {
            userRoomDAO.updateUserRoomRoleByUserRoomId(userRoom.getUserRoomId(), UserRoomRole.ROLE3);
            userRoom = userRooms.get(0);
            userRoomDAO.updateUserRoomRoleByUserRoomId(userRoom.getUserRoomId(), UserRoomRole.ROLE1);
        }else {
            DeleteRoomInDTO deleteRoomInDTO = new DeleteRoomInDTO(userRoom.getRoom().getRoomId(), userRoom.getUser().getUserId());
            if (roomDAO.deleteRoom(deleteRoomInDTO)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean checkUserExist(long userId, long roomId) {
        UserRoom userRoom = userRoomDAO.readUserRoomByUserIdAndRoomId(userId, roomId);
        if(userRoom != null) {
            return true;
        }else {
            return false;
        }
    }
}
