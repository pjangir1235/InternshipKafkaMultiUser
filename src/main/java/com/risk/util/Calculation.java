package com.risk.util;

import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Service;
@Service
public class Calculation {
  public Calculation() {
    super();
  }

  public double getPercentage(double toValue, int maxValue, int val) {
    if (toValue >= maxValue) return 0;
    double value = (100 - (toValue / maxValue) * 100) / (100 / (double) val);
    return DoubleRounder.round(value, 1);
  }
  public double getPercentageGreater(double toValue, int maxValue, int val) {
      if (toValue >= maxValue) return val;
      double value = ((toValue / maxValue) * 100) / (100 / (double) val);
      return DoubleRounder.round(value, 1);
    }


  public double  getHour(int duration) {
      int hour;
      hour=duration/60;
      double decimalfinal=hour+((duration%60)/60);
      return DoubleRounder.round(decimalfinal, 1);
  }
  public double convertInFive(double value,int tot) {
      return (value*5)/tot;
  }
  public double getFinalPercentage(double total,double outOf,int percentage) {
      return DoubleRounder.round((total/outOf)*percentage,2);
  }


}
