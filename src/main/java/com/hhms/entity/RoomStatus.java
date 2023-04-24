package com.hhms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="room_status")
public class RoomStatus {
	


		@Id
		//@GeneratedValue(strategy=GenerationType.AUTO)
		@SequenceGenerator(name="room_status_seq",sequenceName="room_status_seq", allocationSize = 1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="room_status_seq")
		@Column(name = "room_status_id")
		private Long roomStatusId;
		
		@Column(name = "room_status")
		private Long roomStatus;

		@Column(name = "description")
		private String description;

		@Column(name = "state")
		private String state;

		public Long getRoomStatusId() {
			return roomStatusId;
		}

		public void setRoomStatusId(Long roomStatusId) {
			this.roomStatusId = roomStatusId;
		}

		public Long getRoomStatus() {
			return roomStatus;
		}

		public void setRoomStatus(Long roomStatus) {
			this.roomStatus = roomStatus;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

	  

		@Override
		public String toString() {
			return "RoomStatus [roomStatusId=" + roomStatusId + ", roomStatus=" + roomStatus + ", description="
					+ description + ", state=" + state + "]";
		}
	
	

}
