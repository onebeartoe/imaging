
// Module names are of the form poly_<inkscape-path-id>().  As a result,
// you can associate a polygon in this OpenSCAD program with the corresponding
// SVG element in the Inkscape document by looking for the XML element with
// the attribute id="inkscape-path-id".

// fudge value is used to ensure that subtracted solids are a tad taller
// in the z dimension than the polygon being subtracted from.  This helps
// keep the resulting .stl file manifold.
fudge = 0.1;

module poly_Selection(h)
{
  scale([25.4/90, -25.4/90, 1]) union()
  {
    linear_extrude(height=h)
      polygon([[-100.000000,-300.000000],[-100.000000,-100.000000],[100.000000,-100.000000],[100.000000,-300.000000],[300.000000,-300.000000],[300.000000,-100.000000],[100.000000,-100.000000],[100.000000,0.000000],[200.000000,0.000000],[200.000000,300.000000],[100.000000,300.000000],[100.000000,200.000000],[-100.000000,200.000000],[-100.000000,300.000000],[-200.000000,300.000000],[-200.000000,0.000000],[-100.000000,0.000000],[-100.000000,-100.000000],[-300.000000,-100.000000],[-300.000000,-300.000000],[-100.000000,-300.000000]]);
  }
}

module creeperface(h)
{

poly_Selection(h);
}

creeperface(2);
