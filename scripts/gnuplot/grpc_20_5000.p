# output as png image
set terminal png

# save image
set output "./performance/grpc/grpc_20_5000.png"

# graph a title
set title "grpc 20 concurrency 5000 request benchmark"

# nicer aspect ratio for image size
set size 1,0.75

# y-axis grid
set grid y

# x-axis label
set xlabel "request"

# y-axis label
set ylabel "response time (ms)"

# plot data using column 9 with smooth sbezier lines
plot "./performance/grpc/grpc_1_20_5000.dat" using 9 smooth sbezier with lines title "1 data", \
     "./performance/grpc/grpc_50_20_5000.dat" using 9 smooth sbezier with lines title "50 data", \
     "./performance/grpc/grpc_100_20_5000.dat" using 9 smooth sbezier with lines title "100 data", \
     "./performance/grpc/grpc_500_20_5000.dat" using 9 smooth sbezier with lines title "500 data", \
     "./performance/grpc/grpc_1000_20_5000.dat" using 9 smooth sbezier with lines title "1000 data", \
     "./performance/grpc/grpc_5000_20_5000.dat" using 9 smooth sbezier with lines title "5000 data"
