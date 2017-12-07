package com.lvmama.xcl.redisCluster;

/**
 * redis相关的枚举
 *
 */
public class RedisEnum {

	/**
	 * 缓存的Key
	 *
	 */
	public static enum KEY{
		/**
		 * 产品
		 */
		ProdProduct_(60*60*4),
		/**
		 * 商品信息：10分钟
		 */
		SuppGoods_(60*10),
		//分销商缓存一天
		VST_DIST_DISTRIBUTOR_(60*60*24),
		//360凤舞对接xml
		product_api_to_360_fw(-1),
		//微信联系人 缓存1小时
		RouteWeiXinContacts_(60*60*1);
		
		KEY(int s){
			seconds = s;
		}
		/**
		 * 缓存的秒数
		 */
		private int seconds;
		
		public int getSeconds(){
			return this.seconds;
		}
	}
}
