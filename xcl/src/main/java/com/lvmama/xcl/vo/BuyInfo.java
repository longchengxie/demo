/**
 *
 */
package com.lvmama.xcl.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import com.lvmama.xcl.utils.DateUtil;

import net.sf.json.JSONArray;

/**
 * 下单数据
 *
 * @author lancey
 *
 */
public final class BuyInfo implements Serializable {
	private List<Item> itemList;
	
	
	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	private Long productId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	private Long lineRouteId;

	public Long getLineRouteId() {
		return lineRouteId;
	}

	public void setLineRouteId(Long lineRouteId) {
		this.lineRouteId = lineRouteId;
	}

	private String orderCreatingManner;

	public String getOrderCreatingManner() {
		return orderCreatingManner;
	}

	public void setOrderCreatingManner(String orderCreatingManner) {
		this.orderCreatingManner = orderCreatingManner;
	}

	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 酒店特有信息
	 */
	private HotelAdditation hotelAdditation;

	public HotelAdditation getHotelAdditation() {
		return hotelAdditation;
	}

	public void setHotelAdditation(HotelAdditation hotelAdditation) {
		this.hotelAdditation = hotelAdditation;
	}

	/**
	 * 酒店附加属性
	 *
	 * @author lancey
	 *
	 */
	public static class HotelAdditation implements Serializable {
		/**
		 * 序列化ID
		 */
		private static final long serialVersionUID = 1482343196079939833L;

		private String earlyArrivalTime;

		private String arrivalTime;

		private String leaveTime;

		private String stayDays;// 入住几晚,展示用

		private String latestLeaveTime;// 最晚离店时间

		public String getEarlyArrivalTime() {
			return earlyArrivalTime;
		}

		public void setEarlyArrivalTime(String earlyArrivalTime) {
			this.earlyArrivalTime = earlyArrivalTime;
		}

		public String getArrivalTime() {
			return arrivalTime;
		}

		public void setArrivalTime(String arrivalTime) {
			this.arrivalTime = arrivalTime;
		}

		public String getLeaveTime() {
			return leaveTime;
		}

		public void setLeaveTime(String leeaveTime) {
			this.leaveTime = leeaveTime;
		}

		public Date getLeaveTimeDate() {
			return DateUtil.toDate(leaveTime, "yyyy-MM-dd");
		}

		public String getStayDays() {
			return stayDays;
		}

		public void setStayDays(String stayDays) {
			this.stayDays = stayDays;
		}

		public String getLatestLeaveTime() {
			return latestLeaveTime;
		}

		public void setLatestLeaveTime(String latestLeaveTime) {
			this.latestLeaveTime = latestLeaveTime;
		}

	}

	public static class Item implements Serializable {
		/**
		 * 序列化ID
		 */
		private static final long serialVersionUID = 1755069417864830632L;

		private Long goodsId;

		private String goodType;
		
		/**
		 * 酒店特有信息
		 */
		private HotelAdditation hotelAdditation;

		public Long getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(Long goodsId) {
			this.goodsId = goodsId;
		}

		public String getGoodType() {
			return goodType;
		}

		public void setGoodType(String goodType) {
			this.goodType = goodType;
		}

		public HotelAdditation getHotelAdditation() {
			return hotelAdditation;
		}

		public void setHotelAdditation(HotelAdditation hotelAdditation) {
			this.hotelAdditation = hotelAdditation;
		}
		
		

	}

}
