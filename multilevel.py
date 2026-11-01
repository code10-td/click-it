
import numpy as np
import matplotlib.pyplot as plt
import pywt
from scipy.io import wavfile

# ---------- 1. Read the signal ----------
audio_path = r"C:\Users\nitud\Downloads\10,11.wav"

try:
    fs, x = wavfile.read(audio_path)
except Exception as e:
    print("Audio loading error:", e)
    raise SystemExit

# Convert stereo to mono
if x.ndim > 1:
    x = x.mean(axis=1)

# Convert to float
x = x.astype(float)

# Normalize signal
peak = np.max(np.abs(x))

if peak == 0:
    print("Error: Audio signal is silent!")
    raise SystemExit

x = x / peak

# Create time axis
t = np.arange(len(x)) / fs

# ---------- 2. Wavelet settings ----------
wavelet = 'db4'
levels = 4

# Check maximum decomposition level
max_level = pywt.dwt_max_level(len(x), pywt.Wavelet(wavelet).dec_len)

if max_level < levels:
    print("Error: Audio signal is too short for 4-level decomposition.")
    print("Maximum possible level:", max_level)
    raise SystemExit

# ---------- 3. Multilevel decomposition ----------
coeffs = pywt.wavedec(x, wavelet, level=levels)

def reconstruct(keep_index):
    c = [np.zeros_like(a) for a in coeffs]
    c[keep_index] = coeffs[keep_index]
    return pywt.waverec(c, wavelet)[:len(x)]

# Approximation A4
approx = reconstruct(0)

# Details D1 to D4
details = {
    k: reconstruct(levels - k + 1)
    for k in range(1, levels + 1)
}

# Intermediate approximations A1 to A3
approximations = {}

for k in range(1, levels + 1):
    ck = pywt.wavedec(x, wavelet, level=k)
    ck = [ck[0]] + [np.zeros_like(a) for a in ck[1:]]
    approximations[k] = pywt.waverec(
        ck, wavelet
    )[:len(x)]

# ---------- 4. Plot results ----------
plt.figure(figsize=(11, 12))

plt.subplot(levels + 1, 2, 1)
plt.plot(t, x, 'k')
plt.title('Original Signal')
plt.xlabel('Time (s)')

for k in range(1, levels + 1):

    plt.subplot(levels + 1, 2, 2 * k + 1)
    plt.plot(t, approximations[k], 'b')
    plt.title(
        f'Approximation A{k} '
        f'(0 - {fs / 2**(k+1):.0f} Hz)'
    )
    plt.xlabel('Time (s)')

    plt.subplot(levels + 1, 2, 2 * k + 2)
    plt.plot(t, details[k], 'r')
    plt.title(
        f'Detail D{k} '
        f'({fs / 2**(k+1):.0f} - {fs / 2**k:.0f} Hz)'
    )
    plt.xlabel('Time (s)')

plt.tight_layout()
plt.show()

# ---------- 5. Reconstruction check ----------
recon = approx + sum(details.values())

error = np.max(np.abs(x - recon))

print("Multilevel DWT Analysis Completed.")
print("Sampling Frequency:", fs, "Hz")
print("Wavelet:", wavelet)
print("Decomposition Levels:", levels)
print("Maximum Reconstruction Error:", error)
