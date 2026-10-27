import numpy as np
import matplotlib.pyplot as plt
theta = np.linspace(0, np.pi, 220)
phi = np.linspace(0, 2*np.pi, 240)
TH, PH = np.meshgrid(theta, phi)
u = 10*np.sin(TH)*np.cos(PH)      # 3 -> 10 (aperture boro, beam narrow)
v = 8*np.sin(TH)*np.sin(PH)       # 2 -> 8
E = np.abs(np.sinc(u/np.pi) * np.sinc(v/np.pi)) * (1 + np.cos(TH))/2   # back radiation komano
E = E / E.max()
E = np.maximum(20*np.log10(np.maximum(E, 1e-6)), -40)   # dB scale
E = (E + 40) / 40                                       # 0..1 e normalize
X = E*np.sin(TH)*np.cos(PH)
Y = E*np.sin(TH)*np.sin(PH)
Z = E*np.cos(TH)
fig = plt.figure()
ax = fig.add_subplot(111, projection="3d")
ax.plot_surface(X, Y, Z, cmap="inferno", linewidth=0)
ax.set_title("Approximate 3D Horn Antenna Pattern")
ax.set_xlabel("X"); ax.set_ylabel("Y"); ax.set_zlabel("Z")
ax.set_box_aspect((1, 1, 1))
ax.set_zlim(-0.3, 1)
plt.tight_layout()
plt.show()
