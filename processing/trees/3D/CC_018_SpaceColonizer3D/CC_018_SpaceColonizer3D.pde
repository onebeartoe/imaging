// Coding Rainbow
// Daniel Shiffman
// http://patreon.com/codingtrain
// Code for: https://youtu.be/JcopTKXt8L8

import peasy.*;

Tree tree;

Tree t2;

Tree bottom;

PeasyCam cam;

float min_dist = 5;
float max_dist = 200;

color g = color(0, 255, 0);

color g2 = color(0, 100, 0);

void setup() {
  size(600, 900, P3D);
  cam = new PeasyCam(this, 500);
  tree = new Tree(g);
  
  t2 = new Tree(g2);
  
  var brown = color(165,42,42);
  
  bottom = new Tree(brown);
  
  println("Setup");
}

void draw() {
  background(51);
  
  tree.show();
  tree.grow();
  
  t2.show();
  t2.grow();
    
pushMatrix();
translate(0, 140, 0);
rotateZ(135);
//rotateZ(135);
  bottom.show();
  bottom.grow();
popMatrix();  
}
