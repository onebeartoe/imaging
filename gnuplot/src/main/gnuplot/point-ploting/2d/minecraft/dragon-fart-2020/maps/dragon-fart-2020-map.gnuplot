
unset key

set xrange [-2500: 2500]
set yrange [-2500: 2500]

set title "Point Plotting"

set xlabel "x axis ->"
set ylabel "y axis ->"

set tics

set style function dots

set parametric

print "2D plot ahead, one moment please ..."

set label "0,0" at 0, 0

plot 'overworld-map.data' using 1:2:3 with labels 

pause -1 "Hit return to continue"

reset

