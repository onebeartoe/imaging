
unset key

set xrange [-700: 700]
set yrange [-700: 700]

set title "Point Plotting"

set xlabel "x axis ->"
set ylabel "y axis ->"

set tics

set style function dots

set parametric

print "2D plot ahead, one moment please ..."

set label "origin" at 0, 0

plot 'map.data' using 1:2:3 with labels 

#plot -222, -5

pause -1 "Hit return to continue"

reset

