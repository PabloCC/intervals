package usantatecla;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntervalTest {
  
  private Point left = new Point(-2.2);
  private Point right = new Point(4.4);
  private IntervalBuilder intervalBuilder;

  @BeforeEach
  public void before(){
    this.left = new Point(-2.2);
    this.right = new Point(4.4);
    this.intervalBuilder = new IntervalBuilder();
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));
    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenInc3ludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWit3hIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithInclude5dValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervalEqualsWhenIntersectThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    Interval interval2 = new IntervalBuilder().open(left.getEquals()).open(right.getEquals()).build();

    assertTrue(interval.intersect(interval2));
  }

  @Test
  public void givenDistinctIntervalsWhenIntersectThenFalse() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    Interval interval2 = new IntervalBuilder().open(6).open(12).build();

    assertFalse(interval.intersect(interval2));
  }

  @Test
  public void givenIntervalOpenAndLeftIncludeWhenIntersectThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    Interval interval2 = new IntervalBuilder().open(1).open(12).build();

    assertTrue(interval.intersect(interval2));
  }

  @Test
  public void givenIntervalOpenAndRightIncludeWhenIntersectThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    Interval interval2 = new IntervalBuilder().open(-8).open(1).build();

    assertTrue(interval.intersect(interval2));
  }

  @Test
  public void givenIntervalsOpenAndFirstIntervalIncludeInSecondIntervalThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    Interval interval2 = new IntervalBuilder().open(-8).open(10).build();

    assertTrue(interval.intersect(interval2));
  }

  @Test
  public void givenIntervalsOpenAndClosedLeftThenFalse() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    Interval interval2 = new IntervalBuilder().closed(right.getEquals()).open(10).build();

    assertFalse(interval.intersect(interval2));
  }

  @Test
  public void givenIntervalClosedLeftThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    Interval interval2 = new IntervalBuilder().closed(right.getEquals()).open(10).build();

    assertTrue(interval.intersect(interval2));
  }

  @Test
  public void givenIntervalsOpenAndClosedRightThenFalse() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    Interval interval2 = new IntervalBuilder().open(-10).closed(left.getEquals()).build();

    assertFalse(interval.intersect(interval2));
  }

  @Test
  public void givenIntervalClosedRightThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    Interval interval2 = new IntervalBuilder().open(-10).closed(left.getEquals()).build();

    assertTrue(interval.intersect(interval2));
  }
}