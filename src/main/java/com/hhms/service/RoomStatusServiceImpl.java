package com.hhms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhms.entity.RoomStatus;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.RoomStatusRepository;

@Service
public class RoomStatusServiceImpl extends BaseServiceImpl<RoomStatus, Long> implements RoomStatusService {

	public RoomStatusServiceImpl(BaseRepository<RoomStatus, Long> baseRepository) {
		super(baseRepository);
	}
	
	@Autowired
	RoomStatusRepository roomStatusRepository;

	@Override
	public List<RoomStatus> getRoomStatusEmp() throws Exception {
		List<RoomStatus> roomStatus = roomStatusRepository.getRoomStatusEmp();
		return roomStatus;
	}

	@Override
	public List<RoomStatus> getRoomStatusManager() throws Exception {
		List<RoomStatus> roomStatus = roomStatusRepository.getRoomStatusManager();
		return roomStatus;
	}

}
