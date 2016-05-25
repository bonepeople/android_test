package com.shownest.android.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 报价单的汇总
 * 
 * @author bonepeople
 *
 */
public class OfferBill
{
	private int _quotationId;//报价单ID
	private double _allTotal;// 总报价
	private double _costTotal;// 杂费
	private double _taxTotal;// 税费
	private double _hydropowerTotal;// 水电费用
	private double _mountTotal;// 安装费用
	private double _teshuTotal;// 特殊费用
	private ArrayList<Double> _room = new ArrayList<Double>();// 卧室
	private ArrayList<Double> _parlour = new ArrayList<Double>();// 客厅
	private ArrayList<Double> _kitchen = new ArrayList<Double>();// 厨房
	private ArrayList<Double> _toilet = new ArrayList<Double>();// 卫生间
	private ArrayList<Double> _balcony = new ArrayList<Double>();// 阳台

	public OfferBill(JSONObject _json) throws JSONException
	{
		this._quotationId = get_int(_json, "quotationId", 0);
		this._costTotal = get_double(_json, "costTotal", 0);
		this._allTotal = get_double(_json, "allTotal", 0);
		this._hydropowerTotal = get_double(_json, "hydropowerTotal", 0);
		this._mountTotal = get_double(_json, "mountTotal", 0);
		this._taxTotal = get_double(_json, "taxTotal", 0);
		this._teshuTotal = get_double(_json, "teshuTotal", 0);

		set_rooms(_json.getJSONArray("roomsTotalList"), "room");
		set_rooms(_json.getJSONArray("parloursTotalList"), "parlour");
		set_rooms(_json.getJSONArray("kitchensTotalList"), "kitchen");
		set_rooms(_json.getJSONArray("toiletsTotalList"), "toilet");
		set_rooms(_json.getJSONArray("balconysTotalList"), "balcony");
	}

	private void set_rooms(JSONArray _array, String _name) throws JSONException
	{
		for (int _temp_i = 0; _temp_i < _array.length(); _temp_i++)
		{
			switch (_name)
			{
			case "room":
				_room.add(_array.getDouble(_temp_i));
				break;
			case "parlour":
				_parlour.add(_array.getDouble(_temp_i));
				break;
			case "kitchen":
				_kitchen.add(_array.getDouble(_temp_i));
				break;
			case "toilet":
				_toilet.add(_array.getDouble(_temp_i));
				break;
			case "balcony":
				_balcony.add(_array.getDouble(_temp_i));
				break;
			}
		}
	}

	private int get_int(JSONObject _json, String _name, int _default) throws JSONException
	{
		int _result = _default;
		if (_json.has(_name))
		{
			String _temp_str = _json.getString(_name);
			if (!_temp_str.equals("null"))
				_result = _json.getInt(_name);
		}
		return _result;
	}

	private double get_double(JSONObject _json, String _name, double _default) throws JSONException
	{
		double _result = _default;
		if (_json.has(_name))
		{
			String _temp_str = _json.getString(_name);
			if (!_temp_str.equals("null"))
				_result = _json.getDouble(_name);
		}
		return _result;
	}

	public int get_quotationId()
	{
		return _quotationId;
	}

	public void set_quotationId(int _quotationId)
	{
		this._quotationId = _quotationId;
	}

	public double get_allTotal()
	{
		return _allTotal;
	}

	public void set_allTotal(double _allTotal)
	{
		this._allTotal = _allTotal;
	}

	public double get_costTotal()
	{
		return _costTotal;
	}

	public void set_costTotal(double _costTotal)
	{
		this._costTotal = _costTotal;
	}

	public double get_taxTotal()
	{
		return _taxTotal;
	}

	public void set_taxTotal(double _taxTotal)
	{
		this._taxTotal = _taxTotal;
	}

	public double get_hydropowerTotal()
	{
		return _hydropowerTotal;
	}

	public void set_hydropowerTotal(double _hydropowerTotal)
	{
		this._hydropowerTotal = _hydropowerTotal;
	}

	public double get_mountTotal()
	{
		return _mountTotal;
	}

	public void set_mountTotal(double _mountTotal)
	{
		this._mountTotal = _mountTotal;
	}

	public double get_teshuTotal()
	{
		return _teshuTotal;
	}

	public void set_teshuTotal(double _teshuTotal)
	{
		this._teshuTotal = _teshuTotal;
	}

	public ArrayList<Double> get_room()
	{
		return _room;
	}

	public void set_room(ArrayList<Double> _room)
	{
		this._room = _room;
	}

	public ArrayList<Double> get_parlour()
	{
		return _parlour;
	}

	public void set_parlour(ArrayList<Double> _parlour)
	{
		this._parlour = _parlour;
	}

	public ArrayList<Double> get_kitchen()
	{
		return _kitchen;
	}

	public void set_kitchen(ArrayList<Double> _kitchen)
	{
		this._kitchen = _kitchen;
	}

	public ArrayList<Double> get_toilet()
	{
		return _toilet;
	}

	public void set_toilet(ArrayList<Double> _toilet)
	{
		this._toilet = _toilet;
	}

	public ArrayList<Double> get_balcony()
	{
		return _balcony;
	}

	public void set_balcony(ArrayList<Double> _balcony)
	{
		this._balcony = _balcony;
	}
}
