package com.hhms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="room")
public class Room {
	

		@Id
		//@GeneratedValue(strategy=GenerationType.AUTO)
		@SequenceGenerator(name="room_seq",sequenceName="room_seq", allocationSize = 1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="room_seq")
		@Column(name = "room_id")
		private Long roomId;
		
		@Column(name = "room")
		private String room;

		@Column(name = "floor")
		private String floor;

	    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
	    @ManyToOne(optional = true)
	    private Employee employee;

		
		public Long getRoomId() {
			return roomId;
		}

		public void setRoomId(Long roomId) {
			this.roomId = roomId;
		}

		public String getRoom() {
			return room;
		}

		public void setRoom(String room) {
			this.room = room;
		}

		public String getFloor() {
			return floor;
		}

		public void setFloor(String floor) {
			this.floor = floor;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		public RoomStatus getRoomStatus() {
			return roomStatus;
		}

		public void setRoomStatus(RoomStatus roomStatus) {
			this.roomStatus = roomStatus;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@JoinColumn(name = "room_status_id", referencedColumnName = "room_status_id")
	    @ManyToOne(optional = false)
	    private RoomStatus roomStatus;
		
		@Column(name = "state")
		private String state;


		@Override
		public String toString() {
			return "Room [roomId=" + roomId + ", room=" + room + ", floor=" + floor + ", employee=" + employee
					+ ", roomStatus=" + roomStatus + ", state=" + state + "]";
		}

	
	
}
